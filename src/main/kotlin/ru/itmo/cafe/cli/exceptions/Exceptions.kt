package ru.itmo.cafe.cli.exceptions

import java.lang.Exception

abstract class CliException: Exception()

class NoSuchMenuItemException(val option: Int): CliException()