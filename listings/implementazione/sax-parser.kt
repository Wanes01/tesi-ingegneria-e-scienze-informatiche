internal fun openNetcdfDataset(file: Path): NetcdfDataset {
    val previous = System.getProperty(SAX_PARSER_FACTORY_KEY)
    System.setProperty(SAX_PARSER_FACTORY_KEY, JDK_XERCES_SAX_PARSER_FACTORY)
    try {
        return NetcdfDatasets.openDataset(file.toString())
    } finally {
        if (previous == null) {
            System.clearProperty(SAX_PARSER_FACTORY_KEY)
        } else {
            System.setProperty(SAX_PARSER_FACTORY_KEY, previous)
        }
    }
}