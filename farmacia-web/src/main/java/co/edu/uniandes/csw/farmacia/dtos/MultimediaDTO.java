package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;


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
 * MultimediaDTO Objeto de transferencia de datos de Multimediaes. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author lm.gonzalezf
 */
public class MultimediaDTO {

    private Long id;
    
    private String nombre;
    
    private String tipo;

    /**
     * Constructor por defecto
     */
    public MultimediaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param Multimedia: Es la entidad que se va a convertir a DTO
     */
    public MultimediaDTO(MultimediaEntity Multimedia) {
        this.id = Multimedia.getId();
        this.nombre = Multimedia.getName();
        this.tipo = Multimedia.getTipo();
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
        return this.nombre;
    }
    
    public void setName(String name)
    {
        this.nombre = name;
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public MultimediaEntity toEntity() {
        MultimediaEntity entity = new MultimediaEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setTipo(tipo);
        return entity;
    }
    
}
