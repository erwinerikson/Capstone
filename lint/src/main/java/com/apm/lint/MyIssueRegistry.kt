@file:Suppress("unused", "RedundantSuppression")

package com.apm.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("unused")
class MyIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(NamingPatternDetector.ISSUE_NAMING_PATTERN)

    override val api = CURRENT_API
}