package br.com.getfood.pagamentos.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    @Bean
    public ModelMapper getModelMapper () {
        ModelMapper modelMapper = new ModelMapper();

        /* Configurando o modelMapper para ignorar campos que venham vazios do JSON
        *  pois estava dando erro de validação quando fazia o PUT
        */
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        //Configurando o modelMapper para buscar resultado exato, pois estou trabalhando com ID
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
