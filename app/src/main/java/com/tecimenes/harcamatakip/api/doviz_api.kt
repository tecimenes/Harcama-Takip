package com.tecimenes.harcamatakip.api

data class doviz_api(val conversion_rates: ResponseCurrencies)

data class ResponseCurrencies(
        val TRY: Float,
        val EUR: Float,
        val USD: Float,
        val GBP: Float,
        val PLN: Float)