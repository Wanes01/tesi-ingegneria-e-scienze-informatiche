internal fun verify(
    file: Path,
    expectedSizeBytes: Long,
    expectedMd5: String? = null,
    // ...
) {
    val actualSize = Files.size(file)
    check(actualSize == expectedSizeBytes) {
        "Size mismatch for '${file.fileName}': expected $expectedSizeBytes bytes, got $actualSize"
    }
    // no checksum advertised: nothing to verify.
    val advertised = expectedMd5 ?: return
    val expected = advertised.lowercase().padStart(MD5_HEX_DIGITS, '0')
    if (!expected.isMd5Hex()) {
        // ...
        return
    }
    val actual = md5Hex(file)
    check(actual == expected) {
        "MD5 mismatch for '${file.fileName}': expected $expected " +
            "(advertised as '$advertised'), got $actual"
    }
}