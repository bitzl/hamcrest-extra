# Hamcrest Extra Matchers
[![Build Status](https://travis-ci.org/bitzl/hamcrest-extra.svg?branch=master)](https://travis-ci.org/bitzl/hamcrest-extra)
[![codecov](https://codecov.io/gh/bitzl/hamcrest-extra/branch/master/graph/badge.svg)](https://codecov.io/gh/bitzl/hamcrest-extra)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/27f8c549f6424a7e88240b53907e4216)](https://www.codacy.com/app/marcus_2/hamcrest-extra?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bitzl/hamcrest-extra&amp;utm_campaign=Badge_Grade)
[![Apache License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)


Useful matchers which are not part of the official Hamcrest.

The `HasPropertyPath` matcher is an extension on the regular `HasProperty` matcher to test deeper nesting. This is for example useful to test Neo4j deserialization:

```java
@Test
public void shouldLoadItemClientAndPetForPetNamedPeter() {
    Item item = repository.loadItemClientAndPet(...);
    assertThat(item, hasPropertyPath("client.pet.name", is("Peter"));
}
```

In some cases, it is enough to know the path exists, no matter what value the property has:

```java
@Test
public void shouldHavePathToPetName() {
    assertThat(item, hasPropertyPath("client.pet.name")); // is the same as
    assertThat(item, hasPropertyPath("client.pet.name", is(anything()));
}
```
