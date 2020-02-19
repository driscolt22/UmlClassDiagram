package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    AppModelTest.class,
    SaveLoaderTest.class
})
public class AppModelTestSuite
{ // no implementation needed; above annotations do the work.
}
