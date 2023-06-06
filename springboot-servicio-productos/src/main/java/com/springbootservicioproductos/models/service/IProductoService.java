package com.springbootservicioproductos.models.service;

import com.springbootservicioproductos.models.entity.*;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);

    public Producto save(Producto producto);
    public void deleteById(Long id);
}