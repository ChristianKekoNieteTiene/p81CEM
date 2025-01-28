use p81CEM;
insert into mascota (id,numChip,nombre,peso,fechaNac,tipo,idVet)
	values (2,'1234', 'boncuk', 3, '2006-08-09','cat', null);

insert into veterinario (id,nif,nombre,direccion,telefono,email)
	values (2,'12345P', 'LOKUM', 'IMNAL', '12639229','LOKUM@gmail.com');

select *
from mascota;

select *
from veterinario;