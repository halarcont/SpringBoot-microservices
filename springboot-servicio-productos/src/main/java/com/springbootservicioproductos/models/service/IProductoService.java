package com.springbootservicioproductos.models.service;

import com.springbootservicioproductos.models.entity.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);
}