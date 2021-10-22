package me.kaique.domain.services

import me.kaique.domain.entities.StoreCategory

class StoreCategoryService(
    private val categories: List<StoreCategory>
) {
    fun getAllCategories(): List<StoreCategory> = categories

    fun findByCategoryCode(storeCategory: StoreCategory): StoreCategory? =
        categories.find { it.categoryCode == storeCategory.categoryCode && it.categoryName == storeCategory.categoryName }
}