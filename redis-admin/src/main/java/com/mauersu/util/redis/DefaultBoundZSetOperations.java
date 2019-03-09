/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mauersu.util.redis;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisZSetCommands.Aggregate;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.connection.RedisZSetCommands.Weights;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * Default implementation for {@link BoundZSetOperations}.
 * 
 * @author Costin Leau
 * @author Christoph Strobl
 */
class DefaultBoundZSetOperations<K, V> extends DefaultBoundKeyOperations<K> implements BoundZSetOperations<K, V> {

	private final ZSetOperations<K, V> ops;

	/**
	 * Constructs a new <code>DefaultBoundZSetOperations</code> instance.
	 * 
	 * @param key
	 * @param oeprations
	 */
	public DefaultBoundZSetOperations(K key, RedisOperations<K, V> operations) {
		super(key, operations);
		this.ops = operations.opsForZSet();
	}

	public Boolean add(V value, double score) {
		return ops.add(getKey(), value, score);
	}

	public Long add(Set<TypedTuple<V>> tuples) {
		return ops.add(getKey(), tuples);
	}

	public Double incrementScore(V value, double delta) {
		return ops.incrementScore(getKey(), value, delta);
	}

	public RedisOperations<K, V> getOperations() {
		return ops.getOperations();
	}

	public Long intersectAndStore(K otherKey, K destKey) {
		return ops.intersectAndStore(getKey(), otherKey, destKey);
	}

	public Long intersectAndStore(Collection<K> otherKeys, K destKey) {
		return ops.intersectAndStore(getKey(), otherKeys, destKey);
	}

	public Set<V> range(long start, long end) {
		return ops.range(getKey(), start, end);
	}

	public Set<V> rangeByScore(double min, double max) {
		return ops.rangeByScore(getKey(), min, max);
	}

	public Set<TypedTuple<V>> rangeByScoreWithScores(double min, double max) {
		return ops.rangeByScoreWithScores(getKey(), min, max);
	}

	public Set<TypedTuple<V>> rangeWithScores(long start, long end) {
		return ops.rangeWithScores(getKey(), start, end);
	}

	public Set<V> reverseRangeByScore(double min, double max) {
		return ops.reverseRangeByScore(getKey(), min, max);
	}

	public Set<TypedTuple<V>> reverseRangeByScoreWithScores(double min, double max) {
		return ops.reverseRangeByScoreWithScores(getKey(), min, max);
	}

	public Set<TypedTuple<V>> reverseRangeWithScores(long start, long end) {
		return ops.reverseRangeWithScores(getKey(), start, end);
	}

	public Long rank(Object o) {
		return ops.rank(getKey(), o);
	}

	public Long reverseRank(Object o) {
		return ops.reverseRank(getKey(), o);
	}

	public Double score(Object o) {
		return ops.score(getKey(), o);
	}

	public Long remove(Object... values) {
		return ops.remove(getKey(), values);
	}

	public Long removeRange(long start, long end) {
		return ops.removeRange(getKey(), start, end);
	}

	public Long removeRangeByScore(double min, double max) {
		return ops.removeRangeByScore(getKey(), min, max);
	}

	public Set<V> reverseRange(long start, long end) {
		return ops.reverseRange(getKey(), start, end);
	}

	public Long count(double min, double max) {
		return ops.count(getKey(), min, max);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundZSetOperations#size()
	 */
	@Override
	public Long size() {
		return zCard();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundZSetOperations#zCard()
	 */
	@Override
	public Long zCard() {
		return ops.zCard(getKey());
	}

	public Long unionAndStore(K otherKey, K destKey) {
		return ops.unionAndStore(getKey(), otherKey, destKey);
	}

	public Long unionAndStore(Collection<K> otherKeys, K destKey) {
		return ops.unionAndStore(getKey(), otherKeys, destKey);
	}

	public DataType getType() {
		return DataType.ZSET;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundZSetOperations#scan(org.springframework.data.redis.core.ScanOptions)
	 */
	@Override
	public Cursor<TypedTuple<V>> scan(ScanOptions options) {
		return ops.scan(getKey(), options);
	}

	/**
	 * @description 
	 * @param otherKeys
	 * @param destKey
	 * @param aggregate
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Long unionAndStore(Collection<K> otherKeys, K destKey,
			Aggregate aggregate)
	{
		return ops.unionAndStore(getKey(), otherKeys, destKey, aggregate);
	}

	/**
	 * @description 
	 * @param otherKeys
	 * @param destKey
	 * @param aggregate
	 * @param weights
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Long unionAndStore(Collection<K> otherKeys, K destKey,
			Aggregate aggregate, Weights weights)
	{
		return ops.unionAndStore(getKey(), otherKeys, destKey, aggregate, weights);
	}

	/**
	 * @description 
	 * @param otherKeys
	 * @param destKey
	 * @param aggregate
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Long intersectAndStore(Collection<K> otherKeys, K destKey,
			Aggregate aggregate)
	{
		return ops.intersectAndStore(getKey(), otherKeys, destKey, aggregate);
	}

	/**
	 * @description 
	 * @param otherKeys
	 * @param destKey
	 * @param aggregate
	 * @param weights
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Long intersectAndStore(Collection<K> otherKeys, K destKey,
			Aggregate aggregate, Weights weights)
	{
		return ops.intersectAndStore(getKey(), otherKeys, destKey, aggregate, weights);
	}

	/**
	 * @description 
	 * @param range
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Set<V> rangeByLex(Range range)
	{
		return ops.rangeByLex(getKey(), range);
	}

	/**
	 * @description 
	 * @param range
	 * @param limit
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Set<V> rangeByLex(Range range, Limit limit)
	{
		return ops.rangeByLex(getKey(), range, limit);
	}
}
