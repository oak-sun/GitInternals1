class QuizBox<T>(item: T) {
    var isChanged: Boolean = false
        private set
    var item: T = item
        get() {
            println("You asked for the item")
            return field
        }
        set(value) {
            println("You changed the item")
            field = value
            isChanged = true
        }
}
