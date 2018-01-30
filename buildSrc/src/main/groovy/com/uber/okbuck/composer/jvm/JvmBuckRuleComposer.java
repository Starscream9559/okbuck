package com.uber.okbuck.composer.jvm;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.uber.okbuck.composer.base.BuckRuleComposer;
import com.uber.okbuck.core.model.base.Scope;
import com.uber.okbuck.core.model.base.Target;
import com.uber.okbuck.core.model.jvm.JvmTarget;

import java.util.HashSet;
import java.util.Set;

public class JvmBuckRuleComposer extends BuckRuleComposer {

    public static String src(final JvmTarget target) {
        return "src_" + target.getName();
    }

    public static String bin(final JvmTarget target) {
        return "bin_" + target.getName();
    }

    public static String test(final JvmTarget target) {
        return "test_" + target.getName();
    }

    /**
     * Get api and implementation target deps.
     * deps = runtimeClasspath(api + implementation + runtimeOnly) intersect
     *        compileClasspath(api + implementation + compileOnly)
     * @param runtime RuntimeClasspath scope
     * @param compile CompileClasspath scope
     * @return Target deps
     */
    public static Set<Target> getTargetDeps(final Scope runtime, final Scope compile) {
        return Sets.intersection(runtime.getTargetDeps(), compile.getTargetDeps());
    }

    /**
     * Get compileOnly target deps.
     * compileOnlyDeps = compileClasspath(api + implementation + compileOnly) -
     *                   runtimeClasspath(api + implementation + runtimeOnly)
     * @param runtime RuntimeClasspath scope
     * @param compile CompileClasspath scope
     * @return CompileOnly Target deps
     */
    public static Set<Target> getTargetProvidedDeps(final Scope runtime, final Scope compile) {
        return Sets.difference(compile.getTargetDeps(), runtime.getTargetDeps());
    }

    /**
     * Get api and implementation external deps.
     * deps = runtimeClasspath(api + implementation + runtimeOnly) intersect
     *        compileClasspath(api + implementation + compileOnly)
     * @param runtime RuntimeClasspath scope
     * @param compile CompileClasspath scope
     * @return External deps
     */
    public static Set<String> getExternalDeps(final Scope runtime, final Scope compile) {
        return Sets.intersection(runtime.getExternalDeps(), compile.getExternalDeps());
    }

    /**
     * Get compileOnly external deps.
     * compileOnlyDeps = compileClasspath(api + implementation + compileOnly) -
     *                   runtimeClasspath(api + implementation + runtimeOnly)
     * @param runtime RuntimeClasspath scope
     * @param compile CompileClasspath scope
     * @return CompileOnly Target deps
     */
    public static Set<String> getExternalProvidedDeps(final Scope runtime, final Scope compile) {
        return Sets.difference(compile.getExternalDeps(), runtime.getExternalDeps());
    }
}
