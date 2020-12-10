package ru.itmo.cafe.exceptions

class OrderNotFoundException(val id: Int) : RuntimeException()
class ActionNotFoundException(val id: Int) : RuntimeException()