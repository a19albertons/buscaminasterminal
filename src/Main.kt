fun main() {
    val buscaminas = Buscaminas()
    println("Dame 2 numero por un espacio para el tamaño de su tablero que sean positivos")
    val tamano= readln().split(" ").map { it.toInt() }
    println("Dame un numero de minas sea coherente y logico con el tamaño de su tablero")
    val numMinas = readln().toInt()
    buscaminas.crearTablero(tamano[0],tamano[1],numMinas)
    mostrarTablero(buscaminas.tableroFinal)
    println("Dame una X y una Y separadas por un espcaio")
    var coordenadas = readln().split(" ")
    println("Indica si es descubrir (D), marcar (M) o desmarcar (' ')")
    var tipo = readln().uppercase().first()
    buscaminas.accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo)
    while (!(buscaminas.esMina(coordenadas[0].toInt(), coordenadas[1].toInt()) && tipo == 'D')) {
        if (buscaminas.ganar()){
            break
        }
        else {
            mostrarTablero(buscaminas.tableroFinal)
        }
        println("Dame una X y una Y separadas por un espcaio")
        coordenadas = readln().split(" ")
        println("Indica si es descubrir (D), marcar (M) o desmarcar (' ')")
        tipo = readln().uppercase().first()
        buscaminas.accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo)
    }
    if (buscaminas.ganar()){
        println("has ganado")
    }
    else {
        println("has perdido estas eran las ubicaciones de las minas")
        mostrarTablero(buscaminas.tableroMinas)

    }




}
fun mostrarTablero(tablero: MutableList<StringBuilder>) {
    var encabezado=0
    for (i in 0 until tablero.size) {
        encabezado++
        if (encabezado==10){
            encabezado=0
        }
        print("$encabezado ")
    }
    println()
    for (i in 0 until tablero.size) {
        for (j in tablero[i]) {
            print("$j ")
        }
        println()
    }
}