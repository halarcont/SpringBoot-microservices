package com.springbootservicioitem.controllers;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootservicioitem.models.Item;
import com.springbootservicioitem.models.Producto;
import com.springbootservicioitem.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @Value("${configuracion.texto}")
    private String texto;

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad) {
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;

    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){

        log.info(texto);
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
}
