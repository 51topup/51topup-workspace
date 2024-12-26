package shop.topup.workspace

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.configuration.Orthography
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.spring.api.DBRider
import org.junit.jupiter.api.Test
import shop.topup.workspace.impl.infra.db.PostgresqlJsonVectorDataTypeFactory

@DataSet(
    value = ["/db/dataset/accounts.xml", "/db/dataset/suppliers.xml"]
)
@DBRider
@DBUnit(
    schema = "public",
    caseInsensitiveStrategy = Orthography.LOWERCASE,
    dataTypeFactoryClass = PostgresqlJsonVectorDataTypeFactory::class
)
class DatasetImporterTest : SpringBootBaseTest() {

    @Test
    @Throws(Exception::class)
    fun testImportDataset() {
        println("all dataset imported")
    }
}