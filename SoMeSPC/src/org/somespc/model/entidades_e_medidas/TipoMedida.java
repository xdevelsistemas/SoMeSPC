/*
 * SoMeSPC - powerful tool for measurement
 * 
 * Copyright (C) 2013 Ciro Xavier Maretto
 * Copyright (C) 2015 Henrique N�spoli Castro, Vin�cius Soares Fonseca
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.html>.
 */
package org.somespc.model.entidades_e_medidas;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Views({
	@View(members = "nome"),
	@View(name = "Simple", members = "nome"),
})
@Tabs({
	@Tab(properties = "nome", editor = "readOnlyListEditor", defaultOrder = "${nome} asc")
})
public class TipoMedida
{

    @Id
    @TableGenerator(name="TABLE_GENERATOR", table="ID_TABLE", pkColumnName="ID_TABLE_NAME", pkColumnValue="TIPO_MEDIDA_ID", valueColumnName="ID_TABLE_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GENERATOR")
     @Hidden
    private Integer id;

    public Integer getId()
    {
	return id;
    }

    public void setId(Integer id)
    {
	this.id = id;
    }

    @Column(length = 255, unique = true)
    @Required
    private String nome;

    @OneToMany(mappedBy = "tipoMedida")
    private Collection<Medida> medida;

    public String getNome()
    {
	return nome;
    }

    public void setNome(String nome)
    {
	this.nome = nome;
    }

    public Collection<Medida> getMedida()
    {
	return medida;
    }

    public void setMedida(Collection<Medida> medida)
    {
	this.medida = medida;
    }

}
