package ru.itmo.cafe.exceptions

class OrderNotFoundException(val id: Int) : RuntimeException() {
    override val message: String
        get() = "Заказ $id не найден"
}

class ActionNotFoundException(val id: Int) : RuntimeException() {
    override val message: String
        get() = "Операции $id не существует"
}

class OrderIsNotReadyException : RuntimeException() {
    override val message: String
        get() = "Подождите, заказ еще не готов"
}

class OrderAlreadyReadyException : RuntimeException() {
    override val message: String
        get() = "Заказ уже готов"
}

class OrderAlreadyPickedException : RuntimeException() {
    override val message: String
        get() = "Заказ уже забрали"
}

class OrderAlreadyCancelledException : RuntimeException() {
    override val message: String
        get() = "Заказ уже отменен"
}

class OrderWasCancelledException : RuntimeException() {
    override val message: String
        get() = "Заказ был отменен"
}