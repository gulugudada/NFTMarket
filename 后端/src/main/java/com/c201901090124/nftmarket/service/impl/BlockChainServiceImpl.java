package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.service.BlockChainService;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.BooleanResponse;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.geth.Geth;
import org.web3j.utils.Convert;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author 小小怪
 * @title BlockChainServiceImpl
 * @create 2023/1/28 10:02
 **/
@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Resource
    private Web3j web3j;

    @Resource
    private Admin admin;

    @Resource
    private Geth geth;

    // 获得区块高度
    @Override
    public Result getBlockHeight(){
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            return Result.ok(blockNumber.getBlockNumber().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result getEthAccounts() {
        try {
            EthAccounts accounts = web3j.ethAccounts().send();
            return Result.ok(accounts);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获得指定账户地址账户余额
    @Override
    public Result getBalance(String address) {
        try {
            // DefaultBlockParameter.valueOf("latest") 可以理解为不同区块数量时链不同的状态  latest就是指最新区块挖出来时链的状态
            EthGetBalance balance = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
            //Wei转换成ETH
            String balance1 = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER).toPlainString().concat("ETH");
            return Result.ok(balance1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 通过区块编号获取区块信息
    @Override
    public Result getBlockByNumber(BigInteger blockNumber) {
        try {
            EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false).send();
            return Result.ok(block);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 通过区块Hash获取区块信息
    @Override
    public Result getBlockByHash(String blockHash) {
        try {
            EthBlock block = web3j.ethGetBlockByHash(blockHash, false).send();
            return Result.ok(block);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 通过交易哈希获取交易信息
    @Override
    public Result getTransactionByHash(String transactionHash) {
        try {
            EthTransaction transaction = web3j.ethGetTransactionByHash(transactionHash).send();
            return Result.ok(transaction);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得指定块编号的区块中排index位的交易
     */
    @Override
    public Result getTransactionByBlockNumberAndIndex(BigInteger blockNumber, BigInteger index) {
        try {
            EthTransaction transaction = web3j.ethGetTransactionByBlockNumberAndIndex(DefaultBlockParameter.valueOf(blockNumber), index).send();
            return Result.ok(transaction);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得指定块Hash的区块中排index位的交易
     */
    @Override
    public Result getTransactionByBlockHashAndIndex(String blockHash, BigInteger index) {
        try {
            EthTransaction transaction = web3j.ethGetTransactionByBlockHashAndIndex(blockHash, index).send();
            return Result.ok(transaction);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解锁账户，发送交易前需要对账户进行解锁
     *
     * @param address  地址
     * @param password 密码
     */
    private boolean unlockAccount(String address, String password) {
        PersonalUnlockAccount account = null;
        try {
            account = admin.personalUnlockAccount(address, password).send();
            return account.accountUnlocked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 交易发起成功后要锁定账号
     *
     * @param address  账号地址
     */
    public void lockAccount(String address) {
//        BooleanResponse response = null;
        try {
            geth.personalLockAccount(address).send();
//            response = geth.personalLockAccount(address).send();
//            return response.success();
        } catch (IOException e) {
            e.printStackTrace();
//            return false;
        }
    }

    /**
     *   发起以太币交易
     *
     * @param from      发送者地址
     * @param to        接受者地址
     * @param value     交易值
     * @param password  发送者账号密码
     */
    @Override
    public Result sendTransaction(String from,String to,BigInteger value,String password) {
        if(unlockAccount(from,password)){
            try {
                Transaction transaction = new Transaction(from,null,null,null,to,value,null);
                String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
                lockAccount(from);
                return Result.ok(transactionHash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.error();
    }

}
