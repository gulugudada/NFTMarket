// SPDX-License-Identifier: MIT
pragma solidity ^0.8.1;
 
import "@openzeppelin/contracts/utils/Context.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
 
contract NFTStore is Context, ERC20 {
    address private owner;
    struct Mints {
        address minter;
	    uint256 amount;
    }
 
    mapping(uint256 => Mints) public tokenToMint;
    mapping(uint256 => uint256) private tokenAmounts;
    /**
     * @dev Constructor that gives _msgSender() all of existing tokens.
     */
    constructor (uint256 tokenNum) ERC20("NFTStore", "NFC") {
        owner = msg.sender;
        _mint(_msgSender(), tokenNum * (10 ** uint256(decimals())));
    }
 
    function mint(address _to,uint256 amount,uint256 tokenId) public {
        require(msg.sender == owner,"Only the owner of this Contract could mint!");  // 本文添加，否则函数 mint 会有逻辑错误
        _mint( _to, amount) ;    // 如果没有添加上一句 require，此处会有逻辑错误，任何人都可以免费获得NFC
        tokenAmounts[tokenId] = amount;
        Mints memory mintted = Mints({
            minter: _to,
            amount: amount
        });
        tokenToMint[tokenId]=mintted;
    }
 
    function getTokenAmount (uint256 tokenId) public view returns (uint256) {
        return tokenAmounts[tokenId];
    }
 
    function gettokenMints(uint256 tokenId) public view returns ( address, uint256){
        Mints memory mintted = tokenToMint[tokenId]; 
        return (mintted.minter,mintted.amount);
    }
    
    function destroy() virtual public {
	    require(msg.sender == owner,"Only the owner of this Contract could destroy It!");
        selfdestruct(payable(owner));
    }
}