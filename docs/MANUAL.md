# Manual

## Introduction
This tool validates a VCF file.

## Example
To run this tool:
```bash
java -jar ValidateVcf-version.jar -i input.vcf -R myReference.fa
```

To get help:
```bash
java -jar ValidateVcf-version.jar --help
Usage: ValidateVcf [options]

  -l <value> | --log_level <value>
        Level of log information printed. Possible levels: 'debug', 'info', 'warn', 'error'
  -h | --help
        Print usage
  -v | --version
        Print version
  -i <file> | --inputVcf <file>
        Vcf file to check
  -R <file> | --reference <file>
        Reference fasta to check vcf file against
  --disableFail
        Do not fail on error. The tool will still exit when encountering an error, but will do so with exit code 0
```

## Output
An error if something is amiss.