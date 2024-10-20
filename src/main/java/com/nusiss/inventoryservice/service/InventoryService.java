package com.nusiss.inventoryservice.service;

public interface InventoryService {

    /**
     * add
     * @param productId
     * @param availableStock
     */
    void save(String authToken, Long productId, int availableStock);

    /**
     * query by productId
     * @param productId
     * @return
     */
    int query(Long productId);

    /**
     * delete
     * @param productId
     */
    void delete(Long productId);

    /**
     * update
     * @param productId
     * @param availableStock
     */
    void update(String authToken, Long productId, int availableStock);

    /**
     * check if the stock number is available for order
     * @param productId
     * @param num
     * @return
     */
    public Boolean checkStock(Long productId, int num);

    /**
     * deductStock after order made
     * @param productId
     * @param num
     * @return
     */
    public Boolean deductStock(Long productId, Integer num);

    /**
     * addStock when rollback
     * @param productId
     * @param num
     * @return
     */
    public Boolean addStock(Long productId, Integer num);
}
