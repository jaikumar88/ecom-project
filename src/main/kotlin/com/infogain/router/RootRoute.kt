package com.infogain.router


import com.infogain.repo.OrderRepositoryImpl
import com.infogain.repo.PaymentRepositoryImpl
import com.infogain.repo.ProductRepositoryImpl
import com.infogain.repo.RoleRepositoryImpl
import com.infogain.repo.UserRepository
import com.infogain.repo.UserRepositoryImpl
import com.infogain.service.OrderServiceImpl
import com.infogain.service.PaymentServiceImpl
import com.infogain.service.ProductServiceImpl
import com.infogain.service.RoleServiceImpl
import com.infogain.service.UserServiceImpl
import io.ktor.server.routing.Route

/**
 * Root route configured to collect all route initiation in this class
 */
fun Route.rootRoutes() {

    /**
     * Hook the user route
     */
    val userRepository: UserRepository = UserRepositoryImpl()
    val userService: UserServiceImpl = UserServiceImpl(userRepository)
    userRoutes(userService)

    /**
     * Hook the product route
     */
    val productRepository: ProductRepositoryImpl = ProductRepositoryImpl()
    val productService: ProductServiceImpl = ProductServiceImpl(productRepository)
    productRoutes(productService)

    /**
     * Hook the payment route
     */
    val paymentRepository: PaymentRepositoryImpl = PaymentRepositoryImpl()
    val paymentService: PaymentServiceImpl = PaymentServiceImpl(paymentRepository)
    paymentRoutes(paymentService)

    /**
     * Hook the order route
     */
    val orderRepository: OrderRepositoryImpl = OrderRepositoryImpl()
    val orderService: OrderServiceImpl = OrderServiceImpl(orderRepository)
    orderRoutes(orderService)

    val roleRepository: RoleRepositoryImpl = RoleRepositoryImpl()
    val roleService: RoleServiceImpl = RoleServiceImpl(roleRepository)
    roleRoutes(roleService)


}