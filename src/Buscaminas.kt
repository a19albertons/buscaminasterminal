import kotlin.random.Random

class Buscaminas {
    lateinit var tablero: MutableList<StringBuilder>
    lateinit var tableroMinas: MutableList<StringBuilder>
    lateinit var tableroBanderas: MutableList<StringBuilder>
    lateinit var tableroFinal:MutableList<StringBuilder>
    var numMinas = 0



    // Controla el descubrimiento del mapa
    fun descubrir_mapa(x:Int, y:Int):Boolean {
//    Control de limites
        if (x !in tablero.indices || y !in tablero[x].indices) return false
//    no eres casilla sin probar
        if (tablero[x][y]!='-')return false

//    Momento/Instruccion que controla si esta posicion tiene
//    o no una mina con la funcion de comprobar_alrededor
        tablero[x][y]=comprobar_alrededor(x,y,tableroMinas)

//    Si sale una sola mina impide que siga este camino
        if (tablero[x][y]==' '){
//    arriba-izquierda
            descubrir_mapa(x - 1, y - 1)

//    arriba
            descubrir_mapa(x - 1, y)

//    arriba-derecha
            descubrir_mapa(x - 1, y + 1)

//    izquierda
            descubrir_mapa(x, y - 1)

//    derecha
            descubrir_mapa(x, y + 1)

//    abajo-izquierda
            descubrir_mapa(x + 1, y - 1)

//    abajo
            descubrir_mapa(x + 1, y)

//    abajo-derecha
            descubrir_mapa(x + 1, y + 1)
        }

        return false
    }

    //Controla las minas alrededor de una posicon dada. Si alguna casilla
// el numero de minas no coincide con el esperado esta aqui el
// problema ya ha sucedido
    fun comprobar_alrededor(x:Int, y:Int, tablero:MutableList<StringBuilder>):Char {
        var contador_minas= 0
//    superior izquierda
        if (x-1 in tablero.indices && y-1 in tablero[x].indices && tablero[x-1][y-1]=='*') {
            contador_minas++
        }
        //    superior
        if (x - 1 in tablero.indices && y in tablero[x].indices && tablero[x - 1][y] == '*') {
            contador_minas++
        }

//    superior derecha
        if (x - 1 in tablero.indices && y + 1 in tablero[x].indices && tablero[x - 1][y + 1] == '*') {
            contador_minas++
        }

//    izquierda
        if (x in tablero.indices && y - 1 in tablero[x].indices && tablero[x][y - 1] == '*') {
            contador_minas++
        }

//    derecha
        if (x in tablero.indices && y + 1 in tablero[x].indices && tablero[x][y + 1] == '*') {
            contador_minas++
        }

//    inferior izquierda
        if (x + 1 in tablero.indices && y - 1 in tablero[x].indices && tablero[x + 1][y - 1] == '*') {
            contador_minas++
        }

//    inferior
        if (x + 1 in tablero.indices && y in tablero[x].indices && tablero[x + 1][y] == '*') {
            contador_minas++
        }

//    inferior derecha
        if (x + 1 in tablero.indices && y + 1 in tablero[x].indices && tablero[x + 1][y + 1] == '*') {
            contador_minas++
        }

//    Devuelve o no un valor numerico dependiendo
//    de lo que indica el contador de minas
        return if (contador_minas!=0) {
            contador_minas.toString().first()
        }
        else {
            ' '
        }

    }




    // fun crear tablero
    fun crearTablero(x: Int, y: Int, numMinas: Int) {
        val temporal = mutableListOf<StringBuilder>()
        for (i in 0 until x) {
            val temporal2 = StringBuilder()
            for (j in 0 until y ) {
                temporal2.append("-")
            }
            temporal.add(temporal2)
        }
//      diferenciación profunda chat gpt
        tablero=temporal.map { StringBuilder(it.toString()) }.toMutableList()
        tableroFinal=temporal.map { StringBuilder(it.toString()) }.toMutableList()
        tableroBanderas=temporal.map { StringBuilder(it.toString()) }.toMutableList()
        anadirMinas(temporal, numMinas)
        tableroMinas=temporal.map { StringBuilder(it.toString()) }.toMutableList()

    }

//    Create by ChatGPT
    fun anadirMinas(temporal: MutableList<StringBuilder> ,numMinas: Int) {
        this.numMinas=numMinas
        val rows = temporal.size
        if (rows == 0) return
        val cols = temporal[0].length
        var placed = 0
        while (placed < numMinas) {
            val i = Random.nextInt(rows)
            val j = Random.nextInt(cols)
            if (temporal[i][j] != '*') {
                temporal[i].setCharAt(j, '*')
                placed++
            }
        }
    }

    fun indicarDescubrimiento(x: Int, y: Int){
        if (tableroBanderas[x-1][y-1]!='B') {
            descubrir_mapa(x-1, y-1)
            actualizarFinal()
        }
    }
    fun marcar(x:Int, y:Int){
        tableroBanderas[x-1][y-1]='*'
        actualizarFinal()
    }
    fun desmarcar( x: Int, y: Int) {
        tableroBanderas[x-1][y-1]='-'
        actualizarFinal()
    }
    fun accionRealizar(x: Int, y: Int, accion: Char){
        if (accion == 'D'){
            indicarDescubrimiento(x, y)
        }
        else if (accion == 'M') {
            marcar(x,y)
        }
        else if (accion == ' '){
            desmarcar(x,y)
        }
    }
    fun actualizarFinal(){
        tableroFinal=tablero.map { StringBuilder(it.toString()) }.toMutableList()
        for (i in 0 until tableroBanderas.size) {
            for (j in 0 until tableroBanderas[i].length) {
                if (tableroBanderas[i][j] == '*') {
                    tableroFinal[i][j] = 'B'
                }
            }
        }
    }
    fun esMina(x:Int, y:Int, tipo:Char):Boolean{
        if (tableroMinas[x-1][y-1] == '*' && tableroBanderas[x-1][y-1] == '-' && tipo == 'D' ) {
            return true
        }
        else {
            return false
        }
    }
    fun ganar():Boolean{
        var numGuiones=0
        for (i in 0 until tablero.size){
            for (j in 0 until tablero[i].length) {
                if (tablero[i][j]=='-') {
                    numGuiones++
                }
            }
        }
        if (numGuiones==numMinas) {
            return true
        }
        else {
            return false
        }
    }
}