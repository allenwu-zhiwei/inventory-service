package com.nusiss.inventoryservice.service.impl;

import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.inventoryservice.mapper.InventoryMapper;
import com.nusiss.commonservice.feign.UserFeignClient;
import com.nusiss.commonservice.entity.User;
import com.nusiss.commonservice.config.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceImplDiffblueTest {

    @Mock
    private InventoryMapper inventoryMapper;

    @Mock
    private UserFeignClient userClient;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Prepare mock behavior
        String authToken = "authToken";
        Long productId = 1L;
        int availableStock = 100;

        // Mock the response from UserFeignClient
        User mockUser = new User();
        mockUser.setUsername("testUser");
        ResponseEntity<ApiResponse<User>> mockResponse = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        when(userClient.getCurrentUserInfo(authToken)).thenReturn(mockResponse);

        // Perform the method
        inventoryService.save(authToken, productId, availableStock);

        // Verify that the insert method was called
        verify(inventoryMapper, times(1)).insert(any(Inventory.class));
    }

    @Test
    void testQuery() {
        Long productId = 1L;
        int availableStock = 100;

        // Prepare mock inventory
        Inventory mockInventory = new Inventory();
        mockInventory.setProductId(productId);
        mockInventory.setAvailableStock(availableStock);

        when(inventoryMapper.selectOne(any())).thenReturn(mockInventory);

        // Call the query method
        int result = inventoryService.query(productId);

        // Assert the result
        assertEquals(availableStock, result);
    }

    @Test
    void testUpdate() {
        String authToken = "authToken";
        Long productId = 1L;
        int availableStock = 50;

        // Mock the response from UserFeignClient
        User mockUser = new User();
        mockUser.setUsername("testUser");
        ResponseEntity<ApiResponse<User>> mockResponse = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        when(userClient.getCurrentUserInfo(authToken)).thenReturn(mockResponse);

        // Call the update method
        inventoryService.update(authToken, productId, availableStock);

        // Verify that the update method was called on the inventoryMapper
        verify(inventoryMapper, times(1)).update(any(Inventory.class), any());
    }

    @Test
    void testCheckStock() {
        Long productId = 1L;
        int availableStock = 100;
        int orderQuantity = 50;

        // Mock the query method response
        when(inventoryService.query(productId)).thenReturn(availableStock);

        // Call checkStock method
        boolean result = inventoryService.checkStock(productId, orderQuantity);

        // Assert the result
        assertTrue(result);
    }

    @Test
    void testDeductStock() {
        Long productId = 1L;
        int availableStock = 100;
        int deductQuantity = 50;

        // Mock the query method response
        when(inventoryService.query(productId)).thenReturn(availableStock);

        // Call deductStock method
        boolean result = inventoryService.deductStock(productId, deductQuantity);

        // Assert the result
        assertTrue(result);

        // Verify that the update method was called
        verify(inventoryMapper, times(1)).update(any(Inventory.class), any());
    }

    @Test
    void testAddStock() {
        Long productId = 1L;
        int availableStock = 100;
        int addQuantity = 50;

        // Mock the query method response
        when(inventoryService.query(productId)).thenReturn(availableStock);

        // Call addStock method
        boolean result = inventoryService.addStock(productId, addQuantity);

        // Assert the result
        assertTrue(result);

        // Verify that the update method was called
        verify(inventoryMapper, times(1)).update(any(Inventory.class), any());
    }
}

/*
package com.nusiss.inventoryservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nusiss.commonservice.feign.UserFeignClient;
import com.nusiss.inventoryservice.domain.dto.InventoryMessage;
import com.nusiss.inventoryservice.domain.entity.Inventory;
import com.nusiss.inventoryservice.mapper.InventoryMapper;

import java.sql.Timestamp;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InventoryServiceImpl.class, RabbitTemplate.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class InventoryServiceImplDiffblueTest {
    @MockBean
    private InventoryMapper inventoryMapper;

    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private UserFeignClient userFeignClient;

    */
/**
     * Test {@link InventoryServiceImpl#save(String, Long, int)} with
     * {@code authToken}, {@code productId}, {@code availableStock}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#save(String, Long, int)}
     *//*

    @Test
    @DisplayName("Test save(String, Long, int) with 'authToken', 'productId', 'availableStock'")
    @Disabled("TODO: Complete this test")
    void testSaveWithAuthTokenProductIdAvailableStock() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@576c676c testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass255, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.save("ABC123", 1L, 1);
    }

    */
/**
     * Test {@link InventoryServiceImpl#query(Long)} with {@code Long}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#query(Long)}
     *//*

    @Test
    @DisplayName("Test query(Long) with 'Long'")
    @Disabled("TODO: Complete this test")
    void testQueryWithLong() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@14e435ac testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass253, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.query(1L);
    }

    */
/**
     * Test {@link InventoryServiceImpl#delete(Long)}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#delete(Long)}
     *//*

    @Test
    @DisplayName("Test delete(Long)")
    @Disabled("TODO: Complete this test")
    void testDelete() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@6aa56512 testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass251, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.delete(1L);
    }

    */
/**
     * Test {@link InventoryServiceImpl#update(String, Long, int)} with
     * {@code authToken}, {@code productId}, {@code availableStock}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#update(String, Long, int)}
     *//*

    @Test
    @DisplayName("Test update(String, Long, int) with 'authToken', 'productId', 'availableStock'")
    @Disabled("TODO: Complete this test")
    void testUpdateWithAuthTokenProductIdAvailableStock() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@79606be testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass256, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.update("ABC123", 1L, 1);
    }

    */
/**
     * Test {@link InventoryServiceImpl#checkStock(Long, int)}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#checkStock(Long, int)}
     *//*

    @Test
    @DisplayName("Test checkStock(Long, int)")
    @Disabled("TODO: Complete this test")
    void testCheckStock() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@17eb77cf testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass249, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.checkStock(1L, 10);
    }

    */
/**
     * Test {@link InventoryServiceImpl#deductStock(Long, Integer)}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#deductStock(Long, Integer)}
     *//*

    @Test
    @DisplayName("Test deductStock(Long, Integer)")
    @Disabled("TODO: Complete this test")
    void testDeductStock() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@e28bb5d testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass250, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.deductStock(1L, 10);
    }

    */
/**
     * Test {@link InventoryServiceImpl#addStock(Long, Integer)}.
     * <ul>
     *   <li>Given {@link Inventory} (default constructor) AvailableStock is one.</li>
     *   <li>When ten.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link InventoryServiceImpl#addStock(Long, Integer)}
     *//*

    @Test
    @DisplayName("Test addStock(Long, Integer); given Inventory (default constructor) AvailableStock is one; when ten; then return 'true'")
    void testAddStock_givenInventoryAvailableStockIsOne_whenTen_thenReturnTrue() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(1);
        inventory.setCreateDatetime(mock(Timestamp.class));
        inventory.setCreateUser("Create User");
        inventory.setInventoryId(1L);
        inventory.setProductId(1L);
        inventory.setUpdateDatetime(mock(Timestamp.class));
        inventory.setUpdateUser("2020-03-01");
        when(inventoryMapper.update(Mockito.<Inventory>any(), Mockito.<Wrapper<Inventory>>any())).thenReturn(1);
        when(inventoryMapper.selectOne(Mockito.<Wrapper<Inventory>>any(), anyBoolean())).thenReturn(inventory);

        // Act
        Boolean actualAddStockResult = inventoryServiceImpl.addStock(1L, 10);

        // Assert
        verify(inventoryMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(inventoryMapper).update(isA(Inventory.class), isA(Wrapper.class));
        assertTrue(actualAddStockResult);
    }

    */
/**
     * Test {@link InventoryServiceImpl#addStock(Long, Integer)}.
     * <ul>
     *   <li>Given {@link InventoryMapper} {@link BaseMapper#update(Object, Wrapper)}
     * return zero.</li>
     *   <li>When ten.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link InventoryServiceImpl#addStock(Long, Integer)}
     *//*

    @Test
    @DisplayName("Test addStock(Long, Integer); given InventoryMapper update(Object, Wrapper) return zero; when ten; then return 'false'")
    void testAddStock_givenInventoryMapperUpdateReturnZero_whenTen_thenReturnFalse() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setAvailableStock(1);
        inventory.setCreateDatetime(mock(Timestamp.class));
        inventory.setCreateUser("Create User");
        inventory.setInventoryId(1L);
        inventory.setProductId(1L);
        inventory.setUpdateDatetime(mock(Timestamp.class));
        inventory.setUpdateUser("2020-03-01");
        when(inventoryMapper.update(Mockito.<Inventory>any(), Mockito.<Wrapper<Inventory>>any())).thenReturn(0);
        when(inventoryMapper.selectOne(Mockito.<Wrapper<Inventory>>any(), anyBoolean())).thenReturn(inventory);

        // Act
        Boolean actualAddStockResult = inventoryServiceImpl.addStock(1L, 10);

        // Assert
        verify(inventoryMapper).selectOne(isA(Wrapper.class), eq(true));
        verify(inventoryMapper).update(isA(Inventory.class), isA(Wrapper.class));
        assertFalse(actualAddStockResult);
    }

    */
/**
     * Test {@link InventoryServiceImpl#queryCurrentUser(String)}.
     * <p>
     * Method under test: {@link InventoryServiceImpl#queryCurrentUser(String)}
     *//*

    @Test
    @DisplayName("Test queryCurrentUser(String)")
    @Disabled("TODO: Complete this test")
    void testQueryCurrentUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3687d8ed testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass254, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.queryCurrentUser("ABC123");
    }

    */
/**
     * Test {@link InventoryServiceImpl#handleOrderMessage(InventoryMessage)}.
     * <p>
     * Method under test:
     * {@link InventoryServiceImpl#handleOrderMessage(InventoryMessage)}
     *//*

    @Test
    @DisplayName("Test handleOrderMessage(InventoryMessage)")
    @Disabled("TODO: Complete this test")
    void testHandleOrderMessage() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@6d930c58 testClass = com.nusiss.inventoryservice.service.impl.DiffblueFakeClass252, locations = [], classes = [com.nusiss.inventoryservice.service.impl.InventoryServiceImpl, org.springframework.amqp.rabbit.core.RabbitTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@6c1c1c39, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6e81754d, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@dbfabf3a, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@ef79ddb, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@7fd4f54b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212)
        //       at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1709)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        inventoryServiceImpl.handleOrderMessage(new InventoryMessage());
    }
}
*/



