package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.BlockChainService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author 小小怪
 * @title BlockChainController
 * @create 2022/9/12 9:30
 **/
@Api(tags="区块链接口类")
@RestController
@CrossOrigin
public class BlockChainController {

    @Resource
    BlockChainService blockChainService;

    @PostMapping("getHeight")
    public Result getBlockHeight() {
        return blockChainService.getBlockHeight();
    }

    @PostMapping("getEthAccounts")
    public Result getEthAccounts() {
        return blockChainService.getEthAccounts();
    }

    @PostMapping("getBalance")
    public Result getBalance(@RequestBody Map<String, String> map) {
        return blockChainService.getBalance(map.get("address"));
    }

    @PostMapping("getBlockByNumber")
    public Result getBlockByNumber(@RequestBody Map<String, String> map) {
        return blockChainService.getBlockByNumber(new BigInteger(map.get("blockNumber")));
    }

    @PostMapping("getBlockByHash")
    public Result getBlockByHash(@RequestBody Map<String, String> map) {
        return blockChainService.getBlockByHash(map.get("blockHash"));
    }

    @PostMapping("getTransactionByHash")
    public Result getTransactionByHash(@RequestBody Map<String, String> map) {
        return blockChainService.getTransactionByHash(map.get("transactionHash"));
    }

    @PostMapping("getTransactionByBlockNumberAndIndex")
    public Result getTransactionByBlockNumberAndIndex(@RequestBody Map<String, String> map) {
        return blockChainService.getTransactionByBlockNumberAndIndex(new BigInteger(map.get("blockNumber")), new BigInteger(map.get("index")));
    }

    @PostMapping("getTransactionByBlockHashAndIndex")
    public Result getTransactionByBlockHashAndIndex(@RequestBody Map<String, String> map) {
        return blockChainService.getTransactionByBlockHashAndIndex(map.get("blockHash"), new BigInteger(map.get("index")));
    }

    @PostMapping("sendTransaction")
    public Result sendTransaction(@RequestBody Map<String, String> map) {
        return blockChainService.sendTransaction(map.get("from"), map.get("to"), new BigInteger(map.get("value")), map.get("password"));
    }

}
