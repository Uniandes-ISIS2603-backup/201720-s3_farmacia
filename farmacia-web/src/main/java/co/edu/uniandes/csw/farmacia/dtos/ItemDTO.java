package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.ItemEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.SuministroEntity;


/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */


/**
 * itemDTO Objeto de transferencia de datos de itemes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author lm.gonzalezf
 */
public class ItemDTO {

    private Long id;
    private int numItemVendidos;
    private int numItemsProvistos;
    private int numItemsEntregados;
    private int costo;
    private ProductoEntity productoAsociado;
    private SuministroEntity suministroAsociado;

 

     public ItemDTO()
    {
    
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param item: Es la entidad que se va a convertir a DTO
     */
    public ItemDTO(ItemEntity item) {
        this.id = item.getId();
        this.numItemVendidos = item.getNumItemsVendidos();
        this.numItemsEntregados = item.getNumItemsEntregados();
        this.numItemsProvistos = item.getNumItemsProvistos();
        this.costo = item.getCosto();
        this.productoAsociado = item.getProductoEntity();
        this.suministroAsociado = item.getSuministroAsociado();
    }



    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public int getNumItemVendidos() {
        return numItemVendidos;
    }

    public void setNumItemVendidos(int numItemVendidos) {
        this.numItemVendidos = numItemVendidos;
    }

    public int getNumItemsProvistos() {
        return numItemsProvistos;
    }

    public void setNumItemsProvistos(int numItemsProvistos) {
        this.numItemsProvistos = numItemsProvistos;
    }

    public int getNumItemsEntregados() {
        return numItemsEntregados;
    }

    public void setNumItemsEntregados(int numItemsEntregados) {
        this.numItemsEntregados = numItemsEntregados;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public ProductoEntity getProductoAsociado() {
        return productoAsociado;
    }

    public void setProductoAsociado(ProductoEntity productoAsociado) {
        this.productoAsociado = productoAsociado;
    }

    public SuministroEntity getSuministroAsociado() {
        return suministroAsociado;
    }

    public void setSuministroAsociado(SuministroEntity suministroAsociado) {
        this.suministroAsociado = suministroAsociado;
    }
    
    

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ItemEntity toEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setId(this.id);
        entity.setCosto(this.costo);
        entity.setNumItemsEntregados(this.numItemsEntregados);
        entity.setNumItemsProvistos(this.numItemsProvistos);
        entity.setNumItemsVendidos(this.numItemVendidos);
        entity.setProductoEntity(this.productoAsociado);
        entity.setSuministroAsociado(this.suministroAsociado);
        return entity;
    }
    
}
