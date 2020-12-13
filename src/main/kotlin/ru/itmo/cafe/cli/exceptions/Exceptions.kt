package ru.itmo.cafe.cli.exceptions

abstract class CliException : Exception()

class NoSuchMenuItemException(val option: Int) : CliException()