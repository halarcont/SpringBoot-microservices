package com.springbootservicioproductos.models.dao;

import com.springbootservicioproductos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
