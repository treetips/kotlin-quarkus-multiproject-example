#!/bin/sh

echo "###############################################################################"
echo "# Gradle Versions"
echo "###############################################################################"
NUMBER_OF_GRADLE_VERSION_ROWS=4
NUMBER_OF_GRADLE_VERSION_COLUMNS=10
NUMBER_OF_VERSIONS=$((NUMBER_OF_GRADLE_VERSION_ROWS * NUMBER_OF_GRADLE_VERSION_COLUMNS))
curl -s https://services.gradle.org/distributions/ \
  | grep -oE 'gradle-[0-9.]+-bin\.zip' \
  | sed 's/gradle-\([0-9.]*\)-bin\.zip/\1/' \
  | sort -uV \
  | tail -"${NUMBER_OF_VERSIONS}" \
  | awk -v COLS="${NUMBER_OF_COLUMNS}" '{
        printf "%s\t", $0;
        if (NR % COLS == 0) {
          print "";
        }
      }
      END {
        if (NR % COLS != 0) {
          print "";
        }
      }'

echo ""

while true; do
  printf "Please enter gradle version: "
  read -r gradleVersion
  if [ -z "${gradleVersion}" ]; then
    echo "Please enter gradle version: "
  else
    break
  fi
done

# https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:adding_wrapper
./gradlew wrapper \
  --gradle-version "${gradleVersion}" \
  --distribution-type bin
