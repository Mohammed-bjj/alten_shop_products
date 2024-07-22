package alten.shop.backend.backEnd.utils.mappers;

import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.input.InputUserDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public interface IMapper {


    public  OutputProductDTO fromProduct( Product product);

    public Product fromInputProductDTO(InputProductDTO inputProductDTO);



}
