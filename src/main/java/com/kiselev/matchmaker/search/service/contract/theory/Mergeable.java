package com.kiselev.matchmaker.search.service.contract.theory;

@FunctionalInterface
public interface Mergeable<MergeableSearch extends Mergeable> {

    MergeableSearch with();
}