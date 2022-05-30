# Math4Kids

_Este proyecto es la base de un juego llamado "Piensa rápido", el cual cuenta con operaciones báscias de suma, resta, multiplicación y división, también cuenta con  niveles, los cuales pueden ser "Fácil, Intermedio, Avanzado y Experto", este juego esta enfocado para los niños de 3er y 4to año de primaria, especificamente es una relacion de un salon de clases con su grupo de alumnos, en el cual el profesor puede medir el rendimiento de sus alumnos en operaciones básicas desde una app aparte  llamada [MathKidAdmin](https://github.com/PaulGuillen/KidMathAdmin)._

## Comenzando 🚀

_Para comenzar debes tener en cuenta que esta aplicación esta solamente esta renderizada para tablets, así que procederemos a ver el especificamiento de la tablet._

```
. API level = 27.
. Resolución = 2560 x 1600.
. Nombre del dispositivo  = Nexus 10.
. Retrato = Solamente renderizado para vertical, portrait horizontal desactivado.
```

<p align="center">
 <img src="https://user-images.githubusercontent.com/43099030/170897222-8c45aca7-cbd8-4a36-a957-b161a21e5c35.png"/>
</p>

_Un poco mas de información a tener en cuenta sobre las tablets [CalidadTablets](https://developer.android.com/docs/quality-guidelines/tablet-app-quality?hl=es-419)_

_Algo importante son los constraintlayout, la división de porcentajes de pantalla [ConstraintLayout](https://www.geeksforgeeks.org/constraintlayout-in-android/)_


### Pre-requisitos 📋

_Tener instalado Android Studio, conocer sobre kotlin, tener conocimiento de firebase (auth-firestore-database-storage)_

```
. MinSdk =  23
. CompileSdk = 31
. TargetSdk = 31
. Gradle JDFK = corretto - 15 Amanzon Corretto version 15.0.2 
```

## Ejecutando los diseños ⚙️

_En esta etapa mostraremos el flujo de la app con capturas de pantalla de la misma app.

_**Onboarding App**_

_Estas son las capturas de patnalla en la cual mostramos la secuencia para llegar al incio de sesión, esta secuencia solo se mostrará una sola vez, ya que utilizamos SharedPreferences para ejecurtalo una única vez._

<p align="center">
 <img src="https://user-images.githubusercontent.com/43099030/171041468-3a2914c2-b572-46ce-a8b6-8619c79582d0.png"/>
</p>

## Video 📄

_En este video se mostrará la compración del redimensionamiento por cada dispoistivo ejecutado, además de mostrar las vistas correspondientes previamente mostradas_


## Autor ✒️

* **Paul Guillen Acuña** - *Mi Repositorio* - [PaulGuillen](https://github.com/PaulGuillen?tab=repositories)
