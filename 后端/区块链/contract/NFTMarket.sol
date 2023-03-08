// SPDX-License-Identifier: MIT
pragma solidity ^0.8.1;

//代币合约  提供许多代币和代币交易相关的方法
import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
//通过提供加一减一的方法使得每个铸造而成的NFT都有一个独一无二的ID
import "@openzeppelin/contracts/utils/Counters.sol";
//针对合约部署的设置
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
 
 
contract NFTMarket is ERC721,  ERC721Enumerable, ERC721URIStorage  {
    using Strings for uint256;
    using Counters for Counters.Counter;
    //每个NFT被铸造时独一无二的ID  （感觉是一个集合）
    Counters.Counter private _tokenIds;
    //代表合约的部署人地址
    address private owner;
    //NFT的所有uri的数组  （获取值的方式为  _tokenURIs[tokenId]）
    mapping (uint256 => string) private _tokenURIs;
    //可以设置好uri的同一前缀
    string private _baseURIextended;
 
    constructor () ERC721("NFTMarket", "NFTMKT") {
        //合约的构造函数  发起者被保存作为合约的部署人地址
    	owner = msg.sender;
	    //currToken = IERC20(_currTokenAddress);
	}
    
    function setBaseURI(string memory baseURI_) external {
        _baseURIextended = baseURI_;
    }
    
    function _setTokenURI(uint256 tokenId, string memory _tokenURI) internal virtual 
        override(ERC721URIStorage){
        require(_exists(tokenId), "ERC721Metadata: URI set of nonexistent token");
        _tokenURIs[tokenId] = _tokenURI;
    }
    
    function _baseURI() internal view virtual override returns (string memory) {
        return _baseURIextended;
    }
    
    //获取tokenId对应的uri
    function tokenURI(uint256 tokenId) public view virtual override(ERC721, ERC721URIStorage) returns (string memory) {
        require(_exists(tokenId), "ERC721Metadata: URI query for nonexistent token");
 
        string memory _tokenURI = _tokenURIs[tokenId];
        string memory base = _baseURI();
        
        if (bytes(base).length == 0) {
            return _tokenURI;
        }
        if (bytes(_tokenURI).length > 0) {
            return string(abi.encodePacked(base, _tokenURI));
        }
        return string(abi.encodePacked(base, tokenId.toString()));
    }
 
    function supportsInterface(bytes4 interfaceId) public view override(ERC721, ERC721Enumerable) returns (bool) {
        return super.supportsInterface(interfaceId);
    }
 
    //在交易前会执行的方法（暂时不需要）
    function _beforeTokenTransfer(address from, address to, uint256 firstTokenId, uint256 batchSize) internal override(ERC721, ERC721Enumerable) {
        super._beforeTokenTransfer(from, to, firstTokenId, batchSize);
    }
 
    function _burn(uint256 tokenId) internal override(ERC721, ERC721URIStorage) {
        super._burn(tokenId);
    }
 
    function burnNFT(uint256 tokenId) public returns (uint256) {
        //销毁NFT  只有NFT的拥有者可以删除
        require(msg.sender == ownerOf(tokenId),"Only the owner of this Token could Burn It!");
        //这个方法在上面重写的
        _burn(tokenId);
	    return tokenId;
    }
 
    //铸造NFT  同时把图片的uri赋值给NFT 
    function mintNFT(address _to,string memory tokenURI_) public returns (uint256){
        //通过
        _tokenIds.increment();
        //递增获得新NFT的id用来铸造NFT
        uint256 newItemId = _tokenIds.current();
        _mint(_to, newItemId);
        _setTokenURI(newItemId, tokenURI_);
        return newItemId;
    }
 
    function transNFT(address _from,address _to,uint256 tokenId) public returns (uint256) {
        //把tokenId对应的NFT交易给_to
        require(msg.sender == ownerOf(tokenId),"Only the owner of this Token could transfer It!");
        transferFrom(_from,_to,tokenId);
        return tokenId;
    }
 
    function destroy() virtual public {
        //只有合约的部署人才可以销毁合约
        require(msg.sender == owner,"Only the owner of this Contract could destroy It!");
        selfdestruct(payable(owner));
    }
}
