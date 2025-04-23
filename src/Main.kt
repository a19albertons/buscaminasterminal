fun main() {
    val buscaminas = Buscaminas()
    println("Elige un modo de juego")
    println("'classic' tablero 8x8 y 9 minas")
    println("'easy' tablero 9x9 y 10 minas")
    println("'medium' tablero 16x16 y 40 minas")
    println("'expert' tablero 30x16 y 99 minas")
    println("'custom' se le hacen preguntas sobre el tamaño del tablero y el numero de minas")
    println("escriba la palbra entre '' simples para iniciar el modo. Si no introduce un valor valido se asume classic")
    val pregunta= readln()
    when (pregunta) {
        "classic" -> buscaminas.crearTablero(8,8,9)
        "easy" -> buscaminas.crearTablero(9,9,10)
        "medium" -> buscaminas.crearTablero(16,16,40)
        "expert" -> buscaminas.crearTablero(16,30,99)
        "custom" -> {
            println("Dame 2 numero por un espacio para el tamaño de su tablero que sean positivos")
            val tamano= readln().split(" ").map { it.toInt() }
            println("Dame un numero de minas sea coherente y logico con el tamaño de su tablero")
            val numMinas = readln().toInt()
            buscaminas.crearTablero(tamano[0],tamano[1],numMinas)
        }
        else -> buscaminas.crearTablero(8,8,9)
    }

    mostrarTablero(buscaminas.tableroDeFinal())
    println("Dame una X (fila) y una Y (columna) separadas por un espcaio")
    var coordenadas = readln().split(" ")
    println("Indica si es descubrir (D), marcar (M) o desmarcar (' ')")
    var tipo = readln().uppercase().first()
    accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo, buscaminas)
    while (!(buscaminas.esMina(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo))) {
        if (buscaminas.ganar()){
            break
        }
        else {
            mostrarTablero(buscaminas.tableroDeFinal())
        }
        println("Dame una X (fila) y una Y (columna) separadas por un espcaio")
        coordenadas = readln().split(" ")
        println("Indica si es descubrir (D), marcar (M) o desmarcar (' ')")
        tipo = readln().uppercase().first()
        accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo, buscaminas)
    }
    if (buscaminas.ganar()){
        println("has ganado")
    }
    else {
        println("has perdido estas eran las ubicaciones de las minas")
        mostrarTablero(buscaminas.tableroDeMinas())

    }




}
fun mostrarTablero(tablero: MutableList<StringBuilder>) {
    var encabezado=0
    for (i in 0..<tablero.size) {
        encabezado++
        if (encabezado==10){
            encabezado=0
        }
        print("$encabezado  ")
    }
    println()
    encabezado=0
    for (i in 0..<tablero.size) {
        encabezado++
        if (encabezado==10){
            encabezado=0
        }
        for (j in tablero[i]) {
            print("$j  ")
        }
        print(encabezado)
        println()
    }
}
fun accionRealizar(x: Int, y: Int, accion: Char,buscaminas: Buscaminas){
    when (accion) {
        'D' -> {
            buscaminas.indicarDescubrimiento(x, y)
        }
        'M' -> {
            buscaminas.marcar(x,y)
        }
        ' ' -> {
            buscaminas.desmarcar(x,y)
        }
    }
}