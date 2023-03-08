package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author 小小怪
 * @title BlockChainService
 * @create 2023/1/28 10:01
 **/
@Service
public interface BlockChainService {
    Result getBlockHeight();
    Result getEthAccounts();
    Result getBalance(String address);
    Result getBlockByNumber(BigInteger blockNumber);
    Result getBlockByHash(String blockHash);
    Result getTransactionByHash(String transactionHash);
    Result getTransactionByBlockNumberAndIndex(BigInteger blockNumber, BigInteger index);
    Result getTransactionByBlockHashAndIndex(String blockHash, BigInteger index);
    Result sendTransaction(String from,String to,BigInteger value,String password);
}
