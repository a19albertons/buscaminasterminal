fun main() {
    val buscaminas = Buscaminas()
    buscaminas.crearTablero(4,4,2)
    mostrarTablero(buscaminas.tableroFinal)
    println("Dame una X y una Y separadas por un espcaio")
    var coordenadas = readln().split(" ")
    println("Indica si es descubrir (D) o marcar (M)")
    var tipo = readln().uppercase().first()
    buscaminas.accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo)
    mostrarTablero(buscaminas.tableroFinal)
    while (!(buscaminas.esMina(coordenadas[0].toInt(), coordenadas[1].toInt()) && tipo == 'D')) {
        println("Dame una X y una Y separadas por un espcaio")
        coordenadas = readln().split(" ")
        println("Indica si es descubrir (D) o marcar (M)")
        tipo = readln().uppercase().first()
        buscaminas.accionRealizar(coordenadas[0].toInt(), coordenadas[1].toInt(), tipo)
        mostrarTablero(buscaminas.tableroFinal)
    }




}
fun mostrarTablero(tablero: MutableList<StringBuilder>) {
    for (i in 0 until tablero.size) {
        for (j in tablero[i]) {
            print(j)
        }
        println()
    }
}