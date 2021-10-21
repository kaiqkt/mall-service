package me.kaique.application.web.ext

//Min 1 uppercase letter.
//Min 1 lowercase letter.
//Min 1 special character.
//Min 1 number.
//Min 8 characters.
//Max 30 characters.

private const val MAIL_REGEX = "^([a-zA-Z0-9!#\$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#\$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)\$"
private const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~\$^+=<>]).{8,20}\$"
private const val CPF_REGEX = "^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}\$"
private const val CNPJ_REGEX = "^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}\$"

fun String.isCpfValid(): Boolean = Regex(CPF_REGEX).matches(this)

fun String.isCnpjValid(): Boolean = Regex(CNPJ_REGEX).matches(this)