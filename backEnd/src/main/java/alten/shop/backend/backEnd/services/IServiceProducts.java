package alten.shop.backend.backEnd.services;

import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.exceptions.ExistProductException;
import alten.shop.backend.backEnd.utils.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public interface IServiceProducts {

    Map<String, List<OutputProductDTO>> productsList() throws ProductNotFoundException;
    OutputProductDTO productFiltredById(Long productID) throws ProductNotFoundException;
    OutputProductDTO saveNewProduct(InputProductDTO inputProductDTO) throws Exception, ExistProductException;
    OutputProductDTO updateProduct(Long productID, InputProductDTO inputProductDTO) throws Exception, ProductNotFoundException;
    void deleteproductfiltredBy(Long productId) throws ProductNotFoundException, Exception;
    void deleteAllProducts() throws Exception;
}
