package co.edu.uniandes.csw.farmacia.dtos;

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
 * SuministroDTO Objeto de transferencia de datos de Suministroes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author lm.gonzalezf
 */
public class SuministroDTO {

    private Long id;
    
    private String name;
    
    private String info;

    /**
     * Constructor por defecto
     */
    public SuministroDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param Suministro: Es la entidad que se va a convertir a DTO
     */
    public SuministroDTO(SuministroEntity Suministro) {
        this.id = Suministro.getId();
        this.name = Suministro.getName();
        this.info = Suministro.getInfo();
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
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String nuevo)
    {
        this.name = nuevo;
    }
    
    public String getInfo()
    {
        return this.info;          
    }
    
    public void setInfo(String info)
    {
        this.info = info;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public SuministroEntity toEntity() {
        SuministroEntity entity = new SuministroEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setInfo(this.info);
        return entity;
    }
    
}
