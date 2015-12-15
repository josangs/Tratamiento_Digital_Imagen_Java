# Tratamiento_Digital_Imagen_Java

### Introducción:
Proyecto final de carrera, realizando una interfaz gráfica en Java utilizando Eclipse. Trata sobre el tratamiento digital de imagen, implementando diferentes filtros para conseguir el resultado esperado. 
Se ha pretendido realizar filtros para mejorar la calidad de la imagen digital, como por ejemplo: filtros de reducción de ruido y filtros morfológicos. Además de filtros para obtener/buscar información desde una imagen de entrada, p.ej: filtros de detección de bordes, segmentación. 
Se ha realizado en Java gracias a que este es un lenguaje multiplataforma y es muy utilizado en el mundo profesional, además presenta una gran versatilidad a la hora de ejecutar la aplicación en diferentes sistemas operativos.
- - -
### Objetivos:
Los objetivos de esta aplicación erán realizar una *app* sencilla, intuitiva y rápida, que falicilite la interacción del usuario final con ella, repasando algunos de los filtros de imagen más relevantes de la asignatura TDI. La aplicación es gratuita y se destina a fines educativos y en pro de un conocimiento libre y global.
- - -
### Filtros de Imagen:

1. **Transformación de Intensidad**:
  * Aclarado -> *LookupTable*.
  * Monocromo -> Utilizar la barra (*JSlider()*) del panel de la derecha para establecer el umbral.
  * Niveles de Gris -> *ColorConvertOp()*.
  * Negativo -> Restar el color de la imagen a 255.
  
2. **Transformación del Color**:
  * Sepia -> Utilizar la barra (*JSlider()*) del panel de la derecha para restarle el valor elegido a la componente de azul.
  
3. **Operación Geométrica**:
  * Invertir Ejes -> realiza la operación efecto espejo. Utilizar el *JCheckBox()* de la derecha del panel principal para elegir entre rotar en el eje Y o X.
  
4. **Reductores de Ruido**:
  * *Mean Filter*: Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*.
  
  | 1/9 | 1/9 | 1/9 |
  |-----|-----|-----|
  | 1/9 | 1/9 | 1/9 |
  | 1/9 | 1/9 | 1/9 |

  * Suavizado Gaussiano: Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. sigma = 1,0. Multiplicar los coeficientes del kernel por x 1/273.
  
  |1|4|7|4|1|
  |---|---|---|---|---|
  |4|16|26|16|4|
  |7|26|41|26|7| 
  |4|16|26|16|4|
  |1|4|7|4|1|
  
  * Filtro Mediana: Utilizar *Raster()* para obtener valor muestras, luego ordenar menor a mayor y después obtener mediana.
  
5. **Realce de Bordes**:
  * *Sharpening*: Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. Destacar los detalles finos de una imagen.
  
  |0|-1|0|
  |----|----|----|
  |-1|5|-1|
  |0|-1|0|
  

  * Filtro Laplaciano: Obtener módulo del gradiente y dirección. Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*.
  
  |0|-1|0|
  |----|----|----|
  |-1|4|-1|
  |0|-1|0|

6. **Detección de Bordes**:
  * *Laplacian of Gaussian* (LoG): Laplaciano resalta regiones que tengan variaciones muy rápidas de intensidad. Gaussiano reducir o suavizar la sensivilidad al ruido. sigma= 1,4. Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*.
  
  |0|0|3|2|2|2|3|0|0|
  |---|---|---|-----|-----|-----|---|---|---|
  |0|2|3|5|5|5|3|2|0|
  |3|3|5|3|0|3|5|3|3|
  |2|5|3|-12|-23|-12|3|5|2|
  |2|5|0|-23|-40|-23|0|5|2|
  |2|5|3|-12|-23|-12|3|5|2|
  |3|3|5|3|0|3|5|3|3|
  |0|2|3|5|5|5|3|2|0|
  |0|0|3|2|2|2|3|0|0|

  * Sobel: cálculo de la magnitud del gradiente. g = sqrt(Gx^2+Gy^2). Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. Kernel Gx y Gy respectivamente.
  
  |-1|0|1|            
  |---|---|---|      
  |-2|0|2|            
  |1|0|1|             
  
  |-1|-2|-1|
  |---|---|---|
  |0|0|0|
  |1|2|1|

  * Prewitt: cálculo de la magnitud del gradiente. g = sqrt(Gx^2+Gy^2). Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. Kernel Gx y Gy respectivamente.

  |-1|0|1|            
  |---|---|---|            
  |-1|0|1|            
  |1|0|1|            
  
  |-1|-1|-1|
  |---|---|---|
  |0|0|0|
  |1|1|1|
  
  * Roberts: buena respuesta ante bordes diagonales, problema extrema sensibilidad al ruido. Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. Kernel Gx y Gy respectivamente.

  |-1|0|            
  |----|---|            
  |0|1|            
  
  |0|-1|
  |---|----|
  |1|0 |
  
  * Canny: 3 operaciones: Reducción de ruido con primera derivada Gaussiana; encontrar la intensidad del gradiente de la imagen calculando el módulo (g = sqrt(Gx^2+Gy^2)) y dirección (alpha(x,y)=tan^-1(Gy/Gx)); *non-maximal suppression* para quedarnos con los valores del gradiente que sean mayores y tengan direcciones del gradiente similares; por último seguimiento por histeresis utilizando dos umbrales uno umbral bajo y otro umbral alto, Umbrales T1<T2.

7. **Operaciones Morfológicas**:

  * Dilatación: adiciona píxeles al contorno de objetos presentes en la imagen. Utilizar métodos *Kernel()*, *ConvolveOp()* y *Filter()*. Utilizar el siguiente elemento estructurante.
  
  |0|1|0|
  |---|---|---|
  |1|1|1|
  |0|1|0|

  * Erosión: elimina píxeles del controno de objetos presentes en la imagen. Se crea una serie de bucles que recorrerán la imagen y compararán el valor de las muestras. Utilizar el siguiente elemento estructurante.
  
  |1|1|1|
  |---|---|---|
  |1|1|1|
  |1|1|1|

- - -

### Elementos de la Interfaz Gráfica:



