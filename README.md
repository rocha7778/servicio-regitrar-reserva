# servicio-regitrar-reserva
Este microservicio se encarga de registrar una reserva utilizando SQS un servicio de mensajeria de AWS  


Estos microservicos conforman la arquitectura para el manejo de  una rerserva en un hotel.

- servicio-consultar-reserva
- servicio-eureka-server
- servicio-zul-server
- proyecto libreria commons



# servicio-consultar-reserva
Este servicio escucha una cola SQS en AWS y guarda la informacion en una bd MySQL y envia una notificacion al usario que su reserva fue realizada con exito.  
Una vez registrada la reserva, se ofrece un punto de acceso para consultar la reserva por id.  

# Proyecto libreria commons  
Este poryecto se debe compilar y generar el jar con el comando mvnw.cmd install para window, y para linux mvnw install

 
