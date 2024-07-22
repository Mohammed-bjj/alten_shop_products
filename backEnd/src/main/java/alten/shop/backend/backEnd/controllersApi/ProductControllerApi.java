package alten.shop.backend.backEnd.controllersApi;


import alten.shop.backend.backEnd.services.IServiceProducts;
import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.exceptions.ExistProductException;
import alten.shop.backend.backEnd.utils.exceptions.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Tag(name = "Product API", description = "CRUD operations for products")
@AllArgsConstructor
public class ProductControllerApi {

    private IServiceProducts serviceProducts;


    @GetMapping("/products")
    @Operation(summary = "Send All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found and returned"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    public ResponseEntity<Map<String, List<OutputProductDTO>>> getAllProducts() throws ProductNotFoundException {
        return ResponseEntity.ok(this.serviceProducts.productsList());
    }


    @GetMapping("/products/{id}")
    @Operation(summary = "Send One Product filtred by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found and returned"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<OutputProductDTO> getOneProducts(@PathVariable(name = "id") Long productID) throws ProductNotFoundException{
        return ResponseEntity.ok(this.serviceProducts.productFiltredById(productID));
    }


    @Operation(summary = "Save new Product only by Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Product saved"),
            @ApiResponse(responseCode = "400", description = "Bad request : Error saving product"),
            @ApiResponse(responseCode = "409", description = "Error saving product: product exist already"),
    })
    @PostMapping("/admin/products")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<OutputProductDTO> saveProduct(@Valid @RequestBody InputProductDTO inputProductDTO) throws Exception, ExistProductException {
            return  ResponseEntity.ok(this.serviceProducts.saveNewProduct(inputProductDTO));
    }


    @Operation(summary = "Update Product filtred by id only by Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Error saving product"),
    })
    @PatchMapping("/admin/products/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<OutputProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody InputProductDTO productDetails) throws Exception, ProductNotFoundException {
        OutputProductDTO updatedProduct = this.serviceProducts.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }


    @Operation(summary = "Delete Product filtred by id only by Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Bad request : Error deleting product"),
    })
    @DeleteMapping("/admin/products/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productID) throws Exception, ProductNotFoundException {
        this.serviceProducts.deleteproductfiltredBy(productID);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Delete All Product only by Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product Delete"),
            @ApiResponse(responseCode = "400", description = "Error Delete all product"),
    })
    @DeleteMapping("/admin/products")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteAllProduct() throws Exception {
        this.serviceProducts.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }
}
