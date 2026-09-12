class TestCopernicusDataStoreProvider : StringSpec({
    // ...
    "completes the OGC workflow on the captured CDS conversation and downloads the asset" {
        FakeHttpServer().use { fake ->
            val tempDir = createTempDirectory()
            val payload = "test".toByteArray()
            // instructs the server how to respond when it receives a request
            fake.replaySubmit(cds)
            fake.replayStatus(cds, accepted)
            fake.replayStatus(cds, successful)
            fake.replayResults(cds, payload.size)
            fake.serveAsset(cds, payload)
            val request = CopernicusRequest(
                fake.baseUrl,
                cds.dataset,
                mapOf("day" to "01")
            )
            standardProvider.fetch(request, tempDir)
            val downloaded = tempDir.resolve(cds.assetName)
            downloaded.shouldExist()
            downloaded.readBytes() shouldBe payload
            // the download must NOT carry the token
            val downloadReq = fake.requests.single { 
                it.route == cds.assetPath
            }
            val tokenHeader = "PRIVATE-TOKEN"
            downloadReq.header(tokenHeader) shouldBe null
            // every other OGC request MUST carry the token
            fake.requests.filter { it !== downloadReq }.forEach {
                it.header(tokenHeader) shouldBe token
            }
        }
    }
    // ...
})
