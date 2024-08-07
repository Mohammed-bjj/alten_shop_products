package alten.shop.backend.backEnd.utils.mappers;

import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.input.InputUserDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import alten.shop.backend.backEnd.utils.enums.StatusStock;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *   Cette classe a la responsabilité de selectionner les données de la couche DAO (Entitty) pour les exposer ou l'inverse
 */
@AllArgsConstructor
@Service
public class MapperImpl implements  IMapper{


    @Override
    public OutputProductDTO fromProduct(Product product) {
        OutputProductDTO outputProductDTO = new OutputProductDTO();
        BeanUtils.copyProperties(product, outputProductDTO);
        return outputProductDTO;
    }

    @Override
    public Product fromInputProductDTO(InputProductDTO inputProductDTO) {
        System.out.println("med : "+ inputProductDTO.getCategory());
        Product product = new Product();
        BeanUtils.copyProperties(inputProductDTO, product);
        return product;
    }
}
