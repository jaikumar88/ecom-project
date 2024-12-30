package com.infogain.config

import com.infogain.tables.ProductsTable
import com.infogain.tables.RoleTable
import com.infogain.tables.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.yaml.snakeyaml.Yaml
import java.io.InputStream

object DatabaseConfig {
    // These will hold database configuration loaded from YAML
    private lateinit var dbUrl: String
    private lateinit var dbUser: String
    private lateinit var dbPassword: String
    private lateinit var dbDriver: String

    fun init() {
        // First, read the YAML configuration
        readYAML()

        // Next, connect to the database using the loaded configuration
        dbConnect()

        // reflection api// can we refer the package// can we filter the specific classes based on the Name/ type(inheritance )
        // that array we need to pass it to create
        // Scan the package for `Table`
        /* val reflections = Reflections("com.infogain.tables", Scanners.SubTypes)

         val tables = reflections.getSubTypesOf(Table::class.java).mapNotNull { it.kotlin.objectInstance }
        // Create the schema for all found tables
        transaction {    SchemaUtils.create(*tables.toTypedArray())
         println("Schema created for tables: ${tables.map { it.tableName }}")}*/
        // Run any database schema creation or migration tasks
        transaction {
            // SchemaUtils.create(YourTables)
            // Add your schema-related operations here

            SchemaUtils.create(UsersTable)
            SchemaUtils.create(ProductsTable)
            SchemaUtils.create(RoleTable)


        }
    }

    fun readYAML() {
        val inputStream: InputStream = this::class.java.getResourceAsStream("/application.yaml")
            ?: throw IllegalArgumentException("application.yaml not found")

        val yaml = Yaml()
        val config = yaml.load(inputStream) as Map<String, Any>

        // Extract the database section
        val dbSection = config["database"] as Map<String, String>

        dbUrl = dbSection["jdbcUrl"]
            ?: throw IllegalArgumentException("jdbcUrl not found in YAML")
        dbUser = dbSection["username"]
            ?: throw IllegalArgumentException("username not found in YAML")
        dbPassword = dbSection["password"]
            ?: throw IllegalArgumentException("password not found in YAML")
        dbDriver = dbSection["driverClassName"]
            ?: throw IllegalArgumentException("driverClassName not found in YAML")
    }

    fun dbConnect() {
        Database.connect(
            url = dbUrl,
            driver = dbDriver,
            user = dbUser,
            password = dbPassword
        )
    }
}