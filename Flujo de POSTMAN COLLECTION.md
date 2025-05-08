## Flujo de Postman y Organización de Endpoints

Para facilitar la evaluación y prueba de la API, se ha organizado una colección de Postman que refleja la estructura funcional de los endpoints. A continuación, se describe la organización por carpetas, con capturas de pantalla para una identificación más sencilla.

**1. Empresa Y administrador:**

Esta carpeta contiene los endpoints relacionados con la gestión de empresas y las acciones de los administradores del sistema.

![Image 1](EmpresaYadministrador.png)

* `POST postCompany`: Permite crear una nueva empresa.
* `PATCH patchEstado`: Permite actualizar el estado de una empresa existente.
* `GET getCompanies`: Permite obtener una lista de todas las empresas.
* `GET getConsumo`: Permite obtener información sobre el consumo asociado a una empresa.

**2. Restriccion de empresa:**

En esta carpeta se encuentran los endpoints para la gestión de restricciones aplicadas a nivel de empresa.

![Image 1](ruta/a/la/imagen1.png) * `POST postRestriccion`: Permite crear una nueva restricción para una empresa.
* `GET getRestricciones`: Permite obtener una lista de las restricciones aplicadas a una empresa.
* `PUT putRestriccion`: Permite actualizar una restricción existente.
* `DEL deleteRestriccion`: Permite eliminar una restricción.

**3. Usuarios y Límites:**

Esta sección agrupa los endpoints para la gestión de usuarios y la administración de límites de uso de los modelos de IA.

![Image 1](ruta/a/la/imagen1.png) * `POST postUsuario`: Permite crear un nuevo usuario.
* `GET getUsuarios`: Permite obtener una lista de todos los usuarios.
* `GET getUsuarioID`: Permite obtener la información de un usuario específico mediante su ID.
* `PUT putActualizar`: Permite actualizar la información de un usuario existente.
* `POST postLimiteGPT`: Permite establecer un límite específico para el uso del modelo GPT.
* `POST postLimiteLLAMA`: Permite establecer un límite específico para el uso del modelo LLAMA.
* `POST postLimiteDEEP`: Permite establecer un límite específico para el uso de un modelo denominado DEEP.
* `GET getTokensConsumidos`: Permite obtener información sobre los tokens consumidos por los usuarios.

**4. Modelos de IA:**

Aquí se encuentran los endpoints para interactuar directamente con los diferentes modelos de Inteligencia Artificial disponibles.

![Image 2](ruta/a/la/imagen2.png)

* `POST postConsultaGPT`: Permite realizar consultas al modelo GPT.
* `POST postConsultaLLAMA`: Permite realizar consultas al modelo LLAMA.
* `POST postConsultaDEEP`: Permite realizar consultas al modelo DEEP.

**5. Registrar roles usuarios:**

Esta carpeta contiene los endpoints para la asignación de diferentes roles a los usuarios del sistema.

![Image 2](ruta/a/la/imagen2.png) * `POST postSPARKYADMIN`: Permite registrar un usuario con el rol de administrador de Sparky.
* `POST postCOMPANYADMIN`: Permite registrar un usuario con el rol de administrador de empresa.
* `POST postROLEUSER`: Permite registrar un usuario con un rol de usuario genérico.

**Nota:** Asegúrate de reemplazar `ruta/a/la/imagen1.png` y `ruta/a/la/imagen2.png` con las rutas correctas donde se encuentren las imágenes en tu proyecto (`Z:\DBP\HACKATON 1 FINAL`).
