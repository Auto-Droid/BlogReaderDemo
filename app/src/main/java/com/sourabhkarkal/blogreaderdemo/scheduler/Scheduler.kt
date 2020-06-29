package com.sourabhkarkal.blogreaderdemo.scheduler

import io.reactivex.Scheduler

interface Scheduler {
    fun mainThread(): Scheduler?
    fun io(): Scheduler?
    fun newThread(): Scheduler?
}