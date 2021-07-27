rootProject.name = "kotlin-boilerplate"

include("shared")

include("context:codi")
findProject(":context:codi")?.name = "codi"

include("app:core")
findProject(":app:core")?.name = "core"

include("app:api")
findProject(":app:api")?.name = "api"
