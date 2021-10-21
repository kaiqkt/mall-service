package me.kaique.resources.helper

fun contentType(domain: String, version: Int) =
    domain.replace(".", "-").replace(" ", "-").let {
        "application/vnd.${it}_v${version}+json"
    }