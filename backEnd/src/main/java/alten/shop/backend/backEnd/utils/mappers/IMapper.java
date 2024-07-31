package alten.shop.backend.backEnd.utils.mappers;

import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import org.springframework.stereotype.Service;


@Service
public interface IMapper {


    public  OutputProductDTO fromProduct( Product product);

    public Product fromInputProductDTO(InputProductDTO inputProductDTO);



}
