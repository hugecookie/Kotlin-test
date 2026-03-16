package org.example

import org.example.model.Product
import org.example.model.ProductCategory
import org.example.model.Space
import org.example.model.SpaceType
import org.example.model.User
import org.example.model.UserRole
import org.example.repository.InMemoryLayoutPlanRepository
import org.example.repository.InMemoryOrderRepository
import org.example.repository.InMemoryProductRepository
import org.example.repository.InMemoryTemplateRepository
import org.example.repository.InMemoryUserRepository
import org.example.service.AdminCatalogService
import org.example.service.BomService
import org.example.service.OrderService
import org.example.service.ProductCatalogService
import org.example.service.SpacePlanningService
import org.example.service.UserService

fun main() {
    val userRepository = InMemoryUserRepository()
    val productRepository = InMemoryProductRepository()
    val layoutPlanRepository = InMemoryLayoutPlanRepository()
    val orderRepository = InMemoryOrderRepository()
    val templateRepository = InMemoryTemplateRepository()

    val userService = UserService(userRepository)
    val productCatalogService = ProductCatalogService(productRepository)
    val spacePlanningService = SpacePlanningService(layoutPlanRepository, productCatalogService)
    val bomService = BomService(spacePlanningService)
    val orderService = OrderService(orderRepository, spacePlanningService, bomService)
    val adminCatalogService = AdminCatalogService(templateRepository, productCatalogService)

    println("=== G-HP USER SETUP ===")
    val designer = userService.createUser(
        User(id = 1, name = "Kim Designer", email = "designer@ghp.com", role = UserRole.DESIGNER)
    )
    userService.createUser(
        User(id = 2, name = "Park Admin", email = "admin@ghp.com", role = UserRole.ADMIN)
    )
    println(userService.getAllUsers())

    println("\n=== PRODUCT CATALOG ===")
    productCatalogService.registerProduct(
        Product(
            id = 1001,
            name = "Base Cabinet 600",
            category = ProductCategory.KITCHEN_CABINET,
            widthMm = 600,
            depthMm = 600,
            heightMm = 720,
            price = 180000
        )
    )
    productCatalogService.registerProduct(
        Product(
            id = 1002,
            name = "Tall Pantry 900",
            category = ProductCategory.STORAGE_CABINET,
            widthMm = 900,
            depthMm = 650,
            heightMm = 2200,
            price = 320000
        )
    )
    productCatalogService.registerProduct(
        Product(
            id = 1003,
            name = "Built-in Induction",
            category = ProductCategory.APPLIANCE,
            widthMm = 600,
            depthMm = 520,
            heightMm = 60,
            price = 450000
        )
    )
    println(productCatalogService.getAllProducts())

    println("\n=== ADMIN TEMPLATE ===")
    adminCatalogService.registerTemplate(
        id = 1,
        name = "Compact Kitchen Starter",
        recommendedSpaceType = SpaceType.KITCHEN,
        recommendedProductIds = listOf(1001, 1003)
    )
    println(adminCatalogService.getAllTemplates())

    println("\n=== SPACE PLANNING ===")
    val plan = spacePlanningService.createPlan(
        id = 501,
        title = "Han Family Kitchen Renewal",
        designer = designer,
        customerName = "Han Sujin",
        space = Space(
            name = "Apartment Kitchen A",
            type = SpaceType.KITCHEN,
            widthMm = 3600,
            depthMm = 2400,
            heightMm = 2300
        )
    )
    spacePlanningService.addProductToPlan(
        planId = plan.id,
        placementId = 1,
        productId = 1001,
        xMm = 0,
        yMm = 0,
        rotationDegrees = 0
    )
    spacePlanningService.addProductToPlan(
        planId = plan.id,
        placementId = 2,
        productId = 1001,
        xMm = 600,
        yMm = 0,
        rotationDegrees = 0
    )
    spacePlanningService.addProductToPlan(
        planId = plan.id,
        placementId = 3,
        productId = 1003,
        xMm = 600,
        yMm = 40,
        rotationDegrees = 0
    )
    spacePlanningService.addNote(plan.id, "고객 요청: 인덕션 중심 배치, 밝은 톤 마감재 적용")
    val reviewedPlan = spacePlanningService.reviewPlan(plan.id)
    println(reviewedPlan)

    println("\n=== BOM ===")
    val bom = bomService.generateBom(plan.id)
    println(bom)

    println("\n=== ORDER ===")
    val order = orderService.createOrder(orderId = 9001, planId = plan.id)
    println(order)

    println("\n=== FINAL PLAN STATUS ===")
    println(spacePlanningService.getPlan(plan.id))
}
