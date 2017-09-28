/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hs.hernandez
 * Created: 24/09/2017
 */

delete from ClienteEntity;
delete from FacturaEntity;

insert into ClienteEntity (id,name,edad) values (1,'Prueba1',12);
insert into ClienteEntity (id,name,edad) values (2,'Prueba2',13);
insert into ClienteEntity (id,name,edad) values (3,'Prueba3',14);
insert into ClienteEntity (id,name,edad) values (4,'Prueba4',15);

insert into FacturaEntity (id, fecha ,totalfactura) values (1,'2000-12-13T00:00:00-05:00',20000);
insert into FacturaEntity (id, fecha ,totalfactura) values (2,'2000-12-13T00:00:00-05:00',20000);
insert into FacturaEntity (id, fecha ,totalfactura) values (3,'2000-12-13T00:00:00-05:00',20000);
insert into FacturaEntity (id, fecha ,totalfactura) values (4,'2000-12-13T00:00:00-05:00',20000); 

